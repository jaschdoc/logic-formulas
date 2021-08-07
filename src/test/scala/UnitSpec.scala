package io.jaschdoc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.{must, should}
import org.scalatest.{Inside, Inspectors, OptionValues}

abstract class UnitSpec extends AnyFlatSpec with should.Matchers
  with must.Matchers with OptionValues with Inside with Inspectors
