package clwater.weatherbykotlin.Model

/**
 * Created by gengzhibo on 17/8/17.
 */
public class Province() {
    var Pname: String  = String()
    var CityList: List<City> = ArrayList<City>()
}
class City() {
    var Cname: String  = String()
    var RegionList: List<Region> = ArrayList<Region>()
}

class Region() {
    var Id: String = String()
    var Rname: String = String()
}