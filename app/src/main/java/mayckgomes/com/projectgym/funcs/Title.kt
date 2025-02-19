package mayckgomes.com.projectgym.funcs

fun String.title():String{
    return this.replaceFirstChar {
        it.uppercase()
    }
}

