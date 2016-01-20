package com.example
import net.kaleidos.hibernate.usertype.JsonbMapType
/**
 * Created by igilfanov on 19.01.2016.
 *
 * demo
 */
class Foo {

    Integer id

    Map bar

    Double bazz

    static transients = ['bazz']

    static constraints = {
        bazz bindable: true
    }

    static mapping = {
        table schema: 'public', name: 'foo', sqlType: 'serial'
        version false
        id generator: 'sequence', sqlType: 'integer'
        bar type: JsonbMapType, sqlType: 'jsonb'
    }

    def beforeInsert(){
        updateBar()
    }

    def beforeUpdate(){
        updateBar()
    }

    def updateBar(){
        bar = [bazz: bazz]
    }
}
