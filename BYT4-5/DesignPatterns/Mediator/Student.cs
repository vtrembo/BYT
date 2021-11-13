using System;

namespace DesignPatterns.Mediator
{
    public class Student : Participant
    {
        public Student(Chatroom chatroom) : base(chatroom) { }

        public override void Notify(string message)
        {
            Console.WriteLine("To student: " + message);
        }
    }
}
