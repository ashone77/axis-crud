import java.sql.DriverManager
//modelling class
data class Users(val id:Int, val name:String)
fun main(args:Array<String>){
    val jdbcUrl="jdbc:mysql://localhost:3306/sampledb"
    val connection=DriverManager.getConnection(jdbcUrl,"root","root")
    println(connection.isValid(0))

    //insert query-create
    val res=connection.createStatement().executeUpdate("insert into Users(name) values('Chandu')")
    if(res > 0){
        println("Successfully inserted a record into db!!!")
    }
    else{
        println("Insert not successful")
    }

    val update_res = connection.createStatement().executeUpdate("update users set name='Bobby' where id=5")
    if(update_res > 0){
        println("Update success")
    } else {
        println("Update failed")
    }

    val delete_res = connection.createStatement().executeUpdate("Delete from users where id=2")
    if(delete_res > 0){
        println("Delete success!")
    } else {
        print("Delete Failed!")
    }

    //fetch the record from database
    val query=connection.prepareStatement("select *  from Users")
    val result=query.executeQuery()
    val Users=mutableListOf<Users>()
    while(result.next()){
        val id=result.getInt("id")
        val name=result.getString("name")
        Users.add(Users(id,name))

    }
    println(Users)
}