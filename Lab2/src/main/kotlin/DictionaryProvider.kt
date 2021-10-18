object DictionaryProvider {
    fun createDictionary(new_dict: DictionaryType): IDictionary{
        lateinit var the_dict:IDictionary
        if(new_dict == DictionaryType.ARRAY_LIST){
            the_dict = ListDictionary
        }
        if(new_dict == DictionaryType.TREE_SET){
            the_dict = TreeSetDictionary
        }
        if(new_dict == DictionaryType.HASH_SET){
            the_dict =  HashSetDictionary
        }
        return the_dict
    }
}