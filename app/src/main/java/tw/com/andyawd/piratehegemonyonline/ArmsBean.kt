package tw.com.andyawd.piratehegemonyonline

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ArmsBean(
    var name: String = BaseConstants.EMPTY_STRING,
    var level: String = BaseConstants.EMPTY_STRING,
    var count: Long = 0
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            BaseConstants.NAME to name,
            BaseConstants.LEVEL to level,
            BaseConstants.COUNT to count
        )
    }
}