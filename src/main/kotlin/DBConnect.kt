import java.sql.DriverManager

data class User(val id:Int, val name: String){

}


fun main(args: Array<String>) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/sampledb"
    val connection = DriverManager.getConnection(jdbcUrl, "root","root")
    println(connection.isValid(0))

    val res = connection.createStatement().executeUpdate("insert into users(name) values('Raj')")
    if(res > 0){
        println("Insert success!")
    } else {
        println("Insert Failed!")
    }

    val query = connection.prepareStatement("Select * from users;")
    val result = query.executeQuery()
    val users = mutableListOf<User>()

    while(result.next()){
        val id = result.getInt("id")
        val name = result.getString("name")
        users.add(User(id, name))

    }

    println(users)


}