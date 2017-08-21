package clwater.weatherbykotlin.Model

/**
 * Created by gengzhibo on 17/8/17.
 */
class Province {
    var Pname = String()
    var CityList= ArrayList<City>()

    class City {
        var Cname = String()
        var RegionList= ArrayList<Region>()

        class Region {
            var Id = String()
            var Rname = String()
        }
    }
}


