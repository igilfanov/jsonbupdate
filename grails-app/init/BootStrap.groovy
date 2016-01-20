import com.example.Foo

class BootStrap {

    def init = { servletContext ->

        def foo = new Foo(bazz:1)

        foo.save(insert:true, flush:true)

        foo.bazz = 2

        foo.save flush: true

        assert 2 == Foo.last().bar.bazz

    }
    def destroy = {
    }
}
