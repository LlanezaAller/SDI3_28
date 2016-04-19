package com.sdi.business.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.sdi.business.Command;
import com.sdi.business.exception.BusinessException;
import com.sdi.persistence.PersistenceException;
import com.sdi.persistence.util.Jpa;

public class CommandExecutor {
	public Object execute(Command command) throws BusinessException {
		
		EntityManager em = Jpa.getManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();

		try {
			Object res = command.execute();
			trx.commit();
			return res;
		} catch (BusinessException bex) {
			if (trx.isActive())
				trx.rollback();
			throw bex;
		} catch (PersistenceException pex) {
			if (trx.isActive())
				trx.rollback();
			throw pex;
		} finally {
			if (em.isOpen())
				em.close();
		}
	}
}
