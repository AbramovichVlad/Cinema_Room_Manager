package cinema

fun main() {
    showMenu()
}
var totalPrice = 0

fun paymentNewHall() {
    println("Enter the number of rows:")
    val numRows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val numEachRow = readLine()!!.toInt()
    var totalCoast = 0
    if (numEachRow * numRows <= 60) {
        totalCoast = numEachRow * numRows * 10
    } else {
        if (numRows % 2 != 0) {
            totalCoast = (numRows / 2) * numEachRow * 10 + ((numRows / 2) + 1) * numEachRow * 8
        } else {
            totalCoast = (numRows / 2) * numEachRow * 10 + (numRows / 2) * numEachRow * 8
        }
    }
    println("Total income: \n $$totalCoast")
}

fun showSeats() {
    val arrayCount = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8)
    val arraySeats = listOf<String>("S", "S", "S", "S", "S", "S", "S", "S")
    println("Cinema:")
    println("  ${arrayCount.joinToString(" ", "", "")}")
    for (i in 0..arrayCount.size - 2) {
        println("${arrayCount[i]} ${arraySeats.joinToString(" ", "", "")}")
    }
}

fun showMenu() {
    val rows = input("Enter the number of rows:")
    val seats = input("Enter the number of seats in each row:")

    val cinema = List(rows) { MutableList(seats) { 'S' } }

    do {
        val choice = input(
            "\n" +
                    "1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit"
        )

        when (choice) {
            1 -> showTheSeats(cinema)
            2 -> buyATicket(cinema)
            3-> showStatistics(cinema)
        }
    } while (choice != 0)

}

fun showStatistics(cinema: List<List<Char>>) {
    if(cinema.isEmpty()){
        println("heloooooow")
    }
val countChoseSeats = countChoseSeats(cinema)
    val currentIcome =coutCurrentIcome(cinema)
val percentageChoseSeats = coutChoseSeatsPercentage(cinema, countChoseSeats)
    val totalCoast = coutTotalCoast(cinema)
    println("Number of purchased tickets: $countChoseSeats")
    println("Percentage: $percentageChoseSeats%")
    println("Current income: $$totalPrice")
    println("Total income: $$totalCoast")
}

fun coutTotalCoast(cinema: List<List<Char>>): Int{
    val rows = cinema.size
    val seats = cinema[0].size
    var totalCoast = 0
    if (seats * rows  <= 60) {
        totalCoast = seats * rows * 10
    } else {
        if (rows % 2 != 0) {
            totalCoast = (rows / 2) * seats * 10 + ((rows / 2) + 1) * seats * 8
        } else {
            totalCoast = (rows / 2) * seats * 10 + (rows / 2) * seats * 8
        }
    }
    return totalCoast
}

fun coutCurrentIcome(cinema: List<List<Char>>): Int{
    val rows = cinema.size
    val seats = cinema[0].size
    var total = 0
    var price = 0
    repeat(rows) { rowIndex: Int ->
        repeat(seats) { seatIndex: Int ->
            if (cinema[rowIndex][seatIndex] == 'B') {

                  if (rows * seats > 60 && rowIndex > rows / 2) {
                    price = 8
                } else {
                      price =  10
                }
                total += price
            }

        }
    }
    return total
}

fun coutChoseSeatsPercentage(cinema: List<List<Char>>, countChoseSeats : Int): String{
    val rows = cinema.size.toDouble()
    val seats = cinema[0].size.toDouble()
    val onePercent = rows * seats / 100
    val resault : Double = countChoseSeats / onePercent
    return String.format("%.2f", resault)
}

fun countChoseSeats(cinema: List<List<Char>>) : Int{
    val rows = cinema.size
    val seats = cinema[0].size
    var countChoseSeats = 0
    repeat(rows) { rowIndex: Int ->
        repeat(seats) { seatIndex: Int ->
            if (cinema[rowIndex][seatIndex] == 'B') {
                countChoseSeats++
            }
        }
    }
    return countChoseSeats
}

fun buyATicket(cinema: List<MutableList<Char>>) {
    val rows = cinema.size
    val seats = cinema[0].size
    println()
    val coast = 0
    val row = input("Enter a row number:")
    val seat = input("Enter a seat number in that row:")

    if(row > rows || seat > seats){
        println("Wrong input!")
    }
    else{
        if (cinema[row - 1][seat - 1] == 'B') {
            println("That ticket has already been purchased!")
            buyATicket(cinema)
        } else {
            cinema[row - 1][seat - 1] = 'B'
            val price = if (rows * seats > 60 && row > rows / 2) {
                8
            } else {
                10
            }
            totalPrice +=price
            println("Ticket price: $$price")
        }

    }
}

fun input(s: String): Int {
    println(s)
    return readLine()!!.toInt()
}

fun showTheSeats(cinema: List<List<Char>>) {
    val rows = cinema.size
    val seats = cinema[0].size
    println()
    println("Cinema:")
    print(" ")
    repeat(seats) {
        print(" ${it + 1}")
    }
    println()

    repeat(rows) { rowIndex: Int ->
        print(rowIndex + 1)
        repeat(seats) { seatIndex: Int ->
            print(" ${cinema[rowIndex][seatIndex]}")
        }
        println()
    }
    println()
}



