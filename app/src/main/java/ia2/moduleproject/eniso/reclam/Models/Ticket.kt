package ia2.moduleproject.eniso.reclam.Models

/**
 * Created by hamza on 5/10/18.
 */

//Equipement , AppUSer, TicketType , TicketState
class Ticket (var id : String,
              var equipment_name: String ,
              var user : String,
              var  Openingtime : String ,
              var Closingtime : String ,
              var ticketType : String,
              var message :String ,
              var ticketState:TicketState)