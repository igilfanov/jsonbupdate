package com.example

/**
 * Created by igilfanov on 19.01.2016.
 */
class PostgresqlExtensionsDialect extends net.kaleidos.hibernate.PostgresqlExtensionsDialect {


    @Override
    public String getDropSequenceString(String sequenceName) {
        // Adding the "if exists" clause to avoid warnings
        //return "drop sequence if exists " + sequenceName;
        return null;
    }

    @Override
    public boolean dropConstraints() {
        // We don't need to drop constraints before dropping tables, that just leads to error
        // messages about missing tables when we don't have a schema in the database
        return false;
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

}

