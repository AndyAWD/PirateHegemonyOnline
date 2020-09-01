package tw.com.andyawd.piratehegemonyonline

data class ArmsBean(
    var name: String = BaseConstants.EMPTY_STRING,
    var level: String = BaseConstants.EMPTY_STRING,
    var count: Long = 0,
    var food: Long = 0,
    var gold: Long = 0,
    var hp: Long = 0,
    var atk: Long = 0
)