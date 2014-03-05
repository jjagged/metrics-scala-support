package com.github.jjagged.metrics

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.{Gauge => CHGauge}
import nl.grons.metrics.scala._

class MetricBuilder(registry: MetricRegistry) {

  /**
   * Registers a new gauge metric.
   *
   * @param name  the name of the gauge
   */
  def gauge[A](name: String)(f: => A): Gauge[A] =
    new Gauge[A](registry.register(name, new CHGauge[A] { def getValue: A = f }))

  /**
   * Creates a new counter metric.
   *
   * @param name  the name of the counter
   */
  def counter(name: String): Counter =
    new Counter(registry.counter(name))

  /**
   * Creates a new histogram metrics.
   *
   * @param name   the name of the histogram
   */
  def histogram(name: String): Histogram =
    new Histogram(registry.histogram(name))

  /**
   * Creates a new meter metric.
   *
   * @param name the name of the meter
   */
  def meter(name: String): Meter =
    new Meter(registry.meter(name))

  /**
   * Creates a new timer metric.
   *
   * @param name the name of the timer
   */
  def timer(name: String): Timer =
    new Timer(registry.timer(name))

}
