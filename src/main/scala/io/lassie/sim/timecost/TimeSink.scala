package io.lassie.sim.timecost

case class Normal(avg: Double, stdDev: Double) {
  def add(n: Normal): Normal = {
    Normal(avg + n.avg, Math.sqrt(avg * avg + n.avg * n.avg))
  }
}

case class TimeSink(what: Seq[String], time: Normal)

object TimeSink {
  def apply(what: String, time: Normal): TimeSink = TimeSink(List(what), time)
  def apply(sinks: TimeSink*): TimeSink = TimeSink(sinks.map(_.what).flatten, sinks.map(_.time).foldLeft(Normal(0, 0))((dist, norm) => dist.add(norm)))

  val driveToPickup              = TimeSink("drive to pickup",                Normal(5, 5))
  val findParking                = TimeSink("find parking",                   Normal(2, 1))
  val walkFromCarToStore         = TimeSink("walk from car to store",         Normal(3, 2))
  val waitForService             = TimeSink("wait for service",               Normal(1, 1))
  val serviceTime                = TimeSink("service time",                   Normal(3, 1.5))
  val walkFromStoreToCar         = TimeSink("walk from store to car",         Normal(3, 2))
  val findRouteToNextDestination = TimeSink("find route to next destination", Normal(1, 0.5))

  val driveToDropoff             = TimeSink("drive to delivery",              Normal(15, 5))
  val findParkingAndPackage      = TimeSink("find parking and package",       Normal(3, 1))
  val walkFromCarToDelivery      = TimeSink("walk from car to delivery",      Normal(2, 1))
  val waitForRecipient           = TimeSink("wait for recipient",             Normal(1, 1))
  val deliverWithPOD             = TimeSink("delivey and POD",                Normal(2, 1))
  val walkFromDeliveryToCar      = TimeSink("walk from delivery to car",      Normal(2, 1))

/*  val missedDelivery             = ???
  val lunchBreak                 = ???
  val timeLost                   = ???
  val trafficDelay               = ???
  val parkingTicket              = ???*/

  val directPickup = TimeSink(driveToPickup, findParking, walkFromCarToStore, waitForService, serviceTime, walkFromStoreToCar, findRouteToNextDestination)
  val directDropoff = TimeSink(driveToDropoff, findParkingAndPackage, walkFromCarToDelivery, waitForRecipient, deliverWithPOD, walkFromDeliveryToCar)

  val wholeProcess = TimeSink(directPickup, directDropoff)
}

