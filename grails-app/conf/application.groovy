grails.gorm.default.mapping = {
    id generator: "assigned"
    version false
}

grails.gorm.default.constraints = {
    '*'(nullable: true)
}

grails.gorm.failOnError = true

grails.plugin.databasemigration.updateOnStart = true

grails.plugin.databasemigration.changelogLocation = "migrations"