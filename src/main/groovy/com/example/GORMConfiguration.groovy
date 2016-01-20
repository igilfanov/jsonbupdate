package com.example

import org.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration
import org.hibernate.HibernateException
import org.hibernate.MappingException
import org.hibernate.dialect.Dialect
import org.hibernate.dialect.HSQLDialect
import org.hibernate.mapping.ForeignKey
import org.hibernate.mapping.Table
import org.hibernate.tool.hbm2ddl.DatabaseMetadata

/**
 * Created by igilfanov on 19.01.2016.
 */
class GORMConfiguration extends GrailsAnnotationConfiguration {

    private static final long serialVersionUID = 1

    private boolean _alreadyProcessed

    @SuppressWarnings("unchecked")
    @Override
    protected void secondPassCompile() throws MappingException {

        super.secondPassCompile()

        if (_alreadyProcessed) {
            return;
        }

        for (Table table : tables.values()) {

            for (Iterator iterator = table.getForeignKeyIterator(); iterator.hasNext();) {

                ForeignKey foreignKey = (ForeignKey) iterator.next()

                iterator.remove()
            }
        }

        _alreadyProcessed = true
    }

    @Override
    public String[] generateSchemaCreationScript(Dialect dialect) throws HibernateException {
        return prune(super.generateSchemaCreationScript(dialect), dialect)
    }

    @Override
    public String[] generateDropSchemaScript(Dialect dialect) throws HibernateException {
        return prune(super.generateDropSchemaScript(dialect), dialect)
    }

    @Override
    public String[] generateSchemaUpdateScript(Dialect dialect, DatabaseMetadata databaseMetadata) throws HibernateException {
        return prune(super.generateSchemaUpdateScript(dialect, databaseMetadata), dialect);
    }

    private String[] prune(String[] script, Dialect dialect) {

        if (dialect instanceof HSQLDialect) {
            return script;
        }

        List<String> pruned = new ArrayList<String>()

        for (String command : script) {
            if (!isIgnored(command)) {
                pruned.add(command)
            }
        }

        return pruned.toArray(new String[pruned.size()])
    }

    private boolean isIgnored(String command) {

        if(command == null){
            return true
        }

        command = command.toLowerCase()

        return (command.startsWith("drop")
                || command.startsWith("create")
                || command.startsWith("alter")
                || command.startsWith("add")
                || command.startsWith("comment"))
    }




}
