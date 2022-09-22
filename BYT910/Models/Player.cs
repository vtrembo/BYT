using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BYT910.Models
{
    public class Player
    {
        public int Name { get; set; }
        public int MaxHealthPoints { get; set; }
        public int CurrentHealthPoints { get; set; }
        public int Level { get; set; }  
        public string Class { get; set; }
        public int StoryCompleted { get; set; }

    }
}
