package models

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.dbio
import slick.dbio.Effect.Read
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


case class User(name: String,id: String, password: String, pno: Int)

class UserModel @Inject()(protected val dbConfigProvider: DatabaseConfigProvider){
  
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db
  
  import dbConfig.driver.api._
  
  private val Users = TableQuery[UserTable]
    
  private class UserTable(tag: Tag) extends Table[User](tag, "USER") {

    def pno = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    
    def name = column[String]("NAME")
    def id = column[String]("ID")
    def password = column[String]("PASSWORD")

    def * = (name, id, password, pno) <> (User.tupled, User.unapply)
  }
  
  def createTable =  {
    val setup = DBIO.seq(
          
        (Users.schema).create
        //Users += User("min","mj","123",0)
        )
        //Users += ("aa",11)
    val setupFuture = db.run(setup)
    
  }
  
  def addData(userData : User) = {
    val setup = DBIO.seq( Users += userData )
        db.run(setup)
  }
  
  def delData(id: String) = {
    //val setup = DBIO.seq( 
  }
  
  
}