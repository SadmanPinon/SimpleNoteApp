import kotlin.random.Random

class RandomInfo {
    private val randomBody = mutableListOf<String>() //List containing possible all the body line options
    private val randomTitle = mutableListOf<String>() //List containing all the possible title choices
    init {

        //Data Source: https://loremipsum.io

        //Adding bodies
        randomBody.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        randomBody.add("Nunc lobortis mattis aliquam faucibus purus. Lorem ipsum dolor sit amet.")
        randomBody.add("Scelerisque purus semper eget duis at tellus. Tortor posuere ac ut consequat semper.")
        randomBody.add("Ac tincidunt vitae semper quis lectus nulla. Convallis a cras semper auctor neque vitae.")
        randomBody.add("Malesuada fames ac turpis egestas maecenas.")
        randomBody.add("aliquam purus sit amet luctus venenatis lectus. Vestibulum sed arcu non odio euismod lacinia.")
        randomBody.add("at quis risus. Mattis rhoncus urna neque viverra justo nec.")
        randomBody.add("Quam viverra orci sagittis eu volutpat odio facilisis mauris.")
        randomBody.add("Adipiscing elit ut aliquam purus sit amet luctus venenatis lectus.")
        randomBody.add("Senectus et netus et malesuada fames ac turpis egestas.")
        randomBody.add("Orci sagittis eu volutpat odio facilisis mauris sit.")
        randomBody.add("Vel turpis nunc eget lorem dolor sed viverra ipsum nunc.")
        randomBody.add("Facilisis gravida neque convallis a cras semper auctor.")
        randomBody.add("Turpis egestas maecenas pharetra convallis.")
        randomBody.add("Scelerisque purus semper eget duis at tellus.")

        //Adding title
        randomTitle.add("semper")
        randomTitle.add("malesuada")
        randomTitle.add("orci")
        randomTitle.add("don")
        randomTitle.add("threads")
        randomTitle.add("oman")
        randomTitle.add("nambu")
        randomTitle.add("tellus")
        randomTitle.add("atale")
        randomTitle.add("dikta")
        randomTitle.add("corna")
        randomTitle.add("lorem")
        randomTitle.add("core")
        randomTitle.add("ipsum")
        randomTitle.add("eget")


    }
    //Fetches a random Title
    fun getRandomTitle() : String{
        return randomTitle[Random.nextInt(randomTitle.size)]
    }

    //Fetches a random body
    fun getRandomBody() : String{

        val numOfLines = 3 // In my implementation, every random body will contain 3 lines.
        var bodyList =  randomBody.asSequence().shuffled().take(numOfLines).toList().toString()
        val filtered = "[]"
        bodyList = bodyList.filterNot { filtered.indexOf(it) > -1 }
        return bodyList

    }

    //Will determine if note is marked important or not
    fun getRandomBoolean() : Boolean{
        return Math.random()<=0.2 //Will roughly ensure one in 5 notes are marked important as specified in A1 requirement
    }
}


