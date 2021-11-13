
namespace DesignPatterns.Mediator
{
    public abstract class Participant
    {
        protected Chatroom chatroom;

        public Participant(Chatroom chatroom)
        {
            this.chatroom = chatroom;
        }
        public abstract void Notify(string message);
        public virtual void Send(string message)
        {
            chatroom.Send(message, this);
        }
    }
}
