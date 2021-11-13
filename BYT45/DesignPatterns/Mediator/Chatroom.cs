
namespace DesignPatterns.Mediator
{
    public abstract class Chatroom
    {
        public abstract void Send(string msg, Participant participant);
    }
}
