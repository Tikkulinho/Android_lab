import java.io.File
import java.util.*

object HashSetDictionary: IDictionary {
    val words = hashSetOf<String>()
    init{
        File(IDictionary.dict_name).forEachLine { this.add(it) }
    }

    override fun add(x :String): Boolean {
        if(x != " "){
            words.add(x)
            return true
        }
        return false
    }

    override fun find(x :String): Boolean {
        val ok = words.find{
            it == x
        }
        if(ok != null){
            return true
        }
        return false
    }

    override fun size(): Int {
        return words.size
    }
}