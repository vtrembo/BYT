
namespace DesignPatterns.Mediator
{
    public class SubjectChatroom : Chatroom
    {
        public Participant Student { get; set; }
        public Participant Teacher { get; set; }
        public override void Send(string msg, Participant participant)
        {
            if (Student == participant)    
                Teacher.Notify(msg);
            else  
                Student.Notify(msg);            
        }
    }
}
