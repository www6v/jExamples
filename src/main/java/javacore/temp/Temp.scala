

object RoomKeyedStateFunction {

  val LEAVE = "leave"
  val JOIN = "join"
  def main(args: Array[String]) {

    var actions: Set[String] = Set();
    actions = actions + ("join23")
    actions = actions + ("join21")
    actions = actions + ("leave21")
//    actions = actions + ("leave23")

    System.out.println("actions" + actions)

    val action = "leave23"
    actions = actions + action
    System.out.println("actions" + actions)

    if( actions.contains(action) ) {
      val index = LEAVE.length
      val id = action.substring(index, action.length)

      actions = actions - (JOIN + id)
      actions = actions - (LEAVE + id)
    }

    System.out.println("actions" + actions)
  }

}