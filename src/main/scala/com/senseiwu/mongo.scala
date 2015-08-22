package com.senseiwu

/**
 * Created by tomek on 8/16/15.
 */

import com.mongodb.casbah.Imports._

object mongo {
  def accessCollection(name:String) = MongoConnection()("poi")("amenity")

  def bar(name:String) = MongoDBObject("name" -> name)
  def bbq(covered:String, fuel:String) = MongoDBObject("covered" -> covered, "fual" -> fuel)
  def biergarten(name:String, website:String) = MongoDBObject("name" -> name, "website" -> website)
  def address(number:Int, name:String, street:String, city:String, country:String, full:String) =
    MongoDBObject(
      "number" -> number,
      "name" -> name,
      "street" -> street,
      "city" -> city,
      "country" -> country,
      "full" -> full
    )

  def area(hamlet:String, suburb:String, subdistrict:String, district:String, province:String, state:String) =
    MongoDBObject(
      "hamlet" -> hamlet,
      "suburb" -> suburb,
      "subdistrict" -> subdistrict,
      "district" -> district,
      "province" -> province,
      "state" -> state
    )

}

object mongotest extends App {
  val mongoConn = MongoConnection()
  val mongoDB = mongoConn("casbah_test")("test_data")
  println(mongoDB.getName)

  val newObj = MongoDBObject("foo" -> "bar",
    "x" -> "y",
    "pie" -> 3.14,
    "spam" -> "eggs")
  mongoDB += newObj
}

object populate extends App {
  //val mongoColl = MongoConnection() ("casbah_test")("test_data")
  MongoConnection()("casbah_test").dropDatabase()
  val mongoColl = MongoConnection() ("casbah_test")("test_data")
  val user1 = MongoDBObject(
    "user" -> "bwmcadams",
    "email" -> "~~brendan~~<AT>10genDOTcom")
  val user2 = MongoDBObject("user" -> "someOtherUser")
  val u1contact = MongoDBObject("phone" -> "1234-899",
    "city" -> "Montreal")
  val user3 = MongoDBObject("user" -> "bwmcadams1",
    "email" -> "~~brendan~~<AT>10genDOTcom")
  user3.put("address", u1contact)
  mongoColl += user1
  mongoColl += user2
  mongoColl += user3
  mongoColl.find()
  // com.mongodb.casbah.MongoCursor =
  // MongoCursor{Iterator[DBObject] with 2 objects.}

  for { x <- mongoColl} yield x

  val q = MongoDBObject("user" -> "bwmcadams1")
  val cursor = mongoColl.find(q)
  println("User: " + cursor.one())
}

object query extends App {
  val mongoColl = MongoConnection() ("casbah_test")("test_data")
  mongoColl.find()
  for { x <- mongoColl} yield println(x)
}