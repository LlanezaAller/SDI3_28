call S:\_common.bat
@java -classpath ../WebContent/WEB-INF/lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing -url jdbc:hsqldb:hsql://localhost/ -driver org.hsqldb.jdbcDriver