package clwater.weatherbykotlin.Utils

/**
 * Created by yszsyf on 2017/8/30.
 */
object TextUtils{
    fun index2WindLtext(index : String) : String{
        val _index = index.toInt()
        when(_index){

            0 -> return "<3级"
            1 -> return "3-4级"
            2 -> return "4-5级"
            3 -> return "5-6级"
            4 -> return "6-7级"
            5 -> return "7-8级"
            6 -> return "8-9级"
            7 -> return "9-10级"
            8 -> return "10-11级"
            9 -> return "11-12级"
            else -> return "未知"
        }
    }
    fun index2WindDtext(index : String) : String{
        val _index = index.toInt()
        when(_index){
            0 -> return ""
            1 -> return "东北风"
            2 -> return "东风"
            3 -> return "东南风"
            4 -> return "南风"
            5 -> return "西南风"
            6 -> return "西风"
            7 -> return "西北风"
            8 -> return "北风"
            9 -> return "旋转风"
            else -> return "未知"
        }
    }

    fun text2index(index: String): String {
        when (index){
            "晴" -> return "00"
            "多云" -> return "01"
            "阴" -> return "02"
            "阵雨" -> return "03"
            "雷阵雨" -> return "04"
            "雷阵雨伴有冰雹" -> return "05"
            "雨夹雪" -> return "06"
            "小雨" -> return "07"
            "中雨" -> return "08"
            "大雨" -> return "09"
            "暴雨" -> return "10"
            "大暴雨" -> return "11"
            "特大暴雨" -> return "12"
            "阵雪" -> return "13"
            "小雪" -> return "14"
            "中雪" -> return "15"
            "大雪" -> return "16"
            "暴雪" -> return "17"
            "雾" -> return "18"
            "冻雨" -> return "19"
            "沙尘暴" -> return "20"
            "小到中雨" -> return "21"
            "中到大雨" -> return "22"
            "大到暴雨" -> return "23"
            "暴雨到大暴雨" -> return "24"
            "大暴雨到特大暴雨" -> return "25"
            "小到中雪" -> return "26"
            "中到大雪" -> return "27"
            "大到暴雪" -> return "28"
            "浮沉" -> return "29"
            "扬沙" -> return "30"
            "强沙尘暴" -> return "31"
            "霾" -> return "53"
            else -> return "99"
        }

    }

    fun index2text(index: String): String {
        when (index){
            "00"-> return "晴"
            "01"-> return "多云"
            "02"-> return "阴"
            "03"-> return "阵雨"
            "04"-> return "雷阵雨"
            "05"-> return "雷阵雨伴有冰雹"
            "06"-> return "雨夹雪"
            "07"-> return "小雨"
            "08"-> return "中雨"
            "09"-> return "大雨"
            "10"-> return "暴雨"
            "11"-> return "大暴雨"
            "12"-> return "特大暴雨"
            "13"-> return "阵雪"
            "14"-> return "小雪"
            "15"-> return "中雪"
            "16"-> return "大雪"
            "17"-> return "暴雪"
            "18"-> return "雾"
            "19"-> return "冻雨"
            "20"-> return "沙尘暴"
            "21"-> return "小到中雨"
            "22"-> return "中到大雨"
            "23"-> return "大到暴雨"
            "24"-> return "暴雨到大暴雨"
            "25"-> return "大暴雨到特大暴雨"
            "26"-> return "小到中雪"
            "27"-> return "中到大雪"
            "28"-> return "大到暴雪"
            "29"-> return "浮沉"
            "30"-> return "扬沙"
            "31"-> return "强沙尘暴"
            "53"-> return "霾"
            else-> return "未知"
        }

    }
}