
namespace DesignPatterns.Memento
{
    public class ClinicMemory
    {
        private Memento _memento;

        public Memento Memento
        {
            set { _memento = value; }
            get { return _memento; }
        }
    }
}
