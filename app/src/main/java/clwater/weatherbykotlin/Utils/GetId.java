package clwater.weatherbykotlin.Utils;

/**
 * Created by gengzhibo on 17/8/1.
 */

public class GetId {
    static int index = -1 ;
    public static int getIndexId(){
        index = index + 1;
        return index;
    }
}
