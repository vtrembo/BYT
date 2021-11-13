using System;

namespace DesignPatterns.Memento
{
    public class Clinic
    {
        private string patientName;
        private string doctorName;
        private string clinicRoom;

        public string PatientName
        {
            get { return patientName; }
            set
            {
                patientName = value;
                Console.WriteLine("Patient name:  " + patientName);
            }
        }

        public string DoctorName
        {
            get { return doctorName; }
            set
            {
                doctorName = value;
                Console.WriteLine("Doctor name: " + doctorName);
            }
        }

        public string ClinicRoom
        {
            get { return clinicRoom; }
            set
            {
                clinicRoom = value;
                Console.WriteLine("Clinic room №" + clinicRoom);
            }
        }
        public Memento SaveMemento()
        {
            Console.WriteLine("\nThe data was saved.\n");
            return new Memento(patientName, doctorName, clinicRoom);
        }

        public void RestoreMemento(Memento memento)
        {
            Console.WriteLine("\nRestoring...\n");
            this.PatientName = memento.PatientName;
            this.DoctorName = memento.DoctorName;
            this.ClinicRoom = memento.ClinicRoom;
        }
    }
}
