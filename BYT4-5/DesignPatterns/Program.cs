using DesignPatterns.Mediator;
using DesignPatterns.Memento;
using DesignPatterns.ObjectPool;
using System;

namespace DesignPatterns
{
    class Program
    {
        static void Main(string[] args)
        {
            #region Memento
            Console.WriteLine("-----Memento Pattern Example-----\n");
            Clinic clinic = new Clinic();
            clinic.PatientName = "Marshmello";
            clinic.DoctorName = "Smith";
            clinic.ClinicRoom = "14";

            ClinicMemory memory = new ClinicMemory();
            memory.Memento = clinic.SaveMemento();

            clinic.PatientName = "Fried Marshmello";
            clinic.DoctorName = "Grill";
            clinic.ClinicRoom = "15";


            clinic.RestoreMemento(memory.Memento);
            #endregion

            #region Mediator
            Console.WriteLine("\n-----Mediator Pattern Example-----\n");
            SubjectChatroom chatroom = new SubjectChatroom();
            Participant student = new Student(chatroom);
            Participant teacher = new Teacher(chatroom);
            chatroom.Student = student;
            chatroom.Teacher = teacher;
            student.Send("Hello. When do we have the second test?");
            teacher.Send("Tomorrow.");
            student.Send("Ohhh cr... Thank you.");
            #endregion
      
            #region Object pool

            Console.WriteLine("\n-----Object pool Pattern Example-----\n");
            Factory factory = new Factory();

            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);
            Equipment eq1 = factory.GetEquipment();
            Equipment eq2 = factory.GetEquipment();
            Equipment eq3 = factory.GetEquipment(); 
            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);
            Equipment eq4 = factory.GetEquipment();
            Equipment eq5 = factory.GetEquipment();
            Equipment eq6 = factory.GetEquipment();
            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);
            factory.DeleteEquipment(eq1);
            factory.DeleteEquipment(eq2);
            factory.DeleteEquipment(eq3);
            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);
            Equipment eq7 = factory.GetEquipment();
            Equipment eq8 = factory.GetEquipment();
            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);
            Equipment eq9 = factory.GetEquipment();
            Equipment eq10 = factory.GetEquipment();
            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);
            factory.DeleteEquipment(eq4);
            Console.WriteLine("Current amount of objects in pool: " + ObjectPool.Factory.objPool.Count);
            Console.WriteLine("Current amount of acive equipment: " + ObjectPool.Equipment.equipmentCounter);

            #endregion

            Console.Read();
        }
    }
}
