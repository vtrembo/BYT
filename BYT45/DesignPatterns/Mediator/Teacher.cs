using System;

namespace DesignPatterns.Mediator
{
    public class Teacher : Participant
    {
        public Teacher(Chatroom chatroom) : base(chatroom) { }

        public override void Notify(string message)
        {
            Console.WriteLine("To teacher: " + message);
        }
    }
}
