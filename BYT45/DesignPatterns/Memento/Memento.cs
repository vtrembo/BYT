
namespace DesignPatterns.Memento
{
    public class Memento
    {
        private string patientName;
        private string doctorName;
        private string clinicRoom;

        public Memento(string patientName, string doctorName, string clinicRoom)
        {
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.clinicRoom = clinicRoom;
        }

        public string PatientName
        {
            get { return patientName; }
            set { patientName = value; }
        }

        public string DoctorName
        {
            get { return doctorName; }
            set { doctorName = value; }
        }

        public string ClinicRoom
        {
            get { return clinicRoom; }
            set { clinicRoom = value; }
        }
    }
}
