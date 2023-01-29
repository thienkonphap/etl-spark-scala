package org.example

import org.junit._
import Assert._

@Test
class AppTest {

    @Test
    def testOK() = {
        val check = Utility.getCurrentDate("dd/MM/yyyy hh:mm aa")
        println(check)

    }

//    @Test
//    def testKO() = assertTrue(false)

}


