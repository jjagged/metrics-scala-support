package com.github.jjagged.metrics.future

import nl.grons.metrics.scala.Timer
import com.twitter.util.Future
import com.codahale.metrics.{Timer => CHTimer}

class FutureTimer(private val metric: CHTimer) extends Timer(metric) {

  def apply[A <: Any](action: => A): Future[A] = {
    val ctx = this.metric.time()
    Future(action).ensure(ctx.stop())
  }

  def apply[A](action: Future[A]): Future[A] = {
    val ctx = this.metric.time()
    action.ensure(ctx.stop())
  }
}
