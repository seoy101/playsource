package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import slick.dbio
import slick.dbio.Effect.Read
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile



@Singleton
class HomeController @Inject()(userModel: UserModel) extends Controller {
  
  val userForm = Form(
        mapping(
            "name" -> text,
            "id"  -> text,
            "password" -> text,
            "pno" -> number
            )(User.apply)(User.unapply)
      )
  
  
  def index = Action { implicit request =>
    Ok(views.html.index())
    
  }
  
  def makedb = Action { implicit request =>
    userModel.createTable
    Ok("hi")
  }
  
  def addscreen = Action { implicit request =>
    Ok(views.html.addscreen())
  }
  
  def addData = Action { implicit request =>
    val userData = request.body.asFormUrlEncoded.get
     val name = userData.get("name").get(0)
     val id = userData.get("id").get(0)
     val password = userData.get("password").get(0)
     val userman = User(name,id,password,0)
     
     userModel.addData(userman)
     
    Ok(name + "  " + id + "  "  + password +  "  " )
  }
  
}











  /*
  
  
  def login = Action { implicit request =>
    val userData = userForm.bindFromRequest().get
    
    Ok(userData.name + "  " + userData.age)
  }*/
  











