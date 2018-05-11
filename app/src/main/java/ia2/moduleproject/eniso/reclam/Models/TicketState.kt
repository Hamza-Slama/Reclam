package ia2.moduleproject.eniso.reclam.Models

/**
 * Created by hamza on 5/10/18.
 */
//class TicketState (var id : String ,
//                   var name : String ,
//                   var Note : String )
enum class TicketState{

    OPEN,
    ASSIGNED,
    IN_PROGRESS,
    VALIDATION,
    CLOSED
}