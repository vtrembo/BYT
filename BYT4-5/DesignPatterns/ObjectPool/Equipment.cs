using System;

namespace DesignPatterns.ObjectPool
{
    class Equipment
    {
        public static int equipmentMaxSize = 3;
        public static int equipmentCounter = 0;
        public Equipment()    {   ++equipmentCounter;   }
        public Equipment(String equipmentForPool) {
            Console.WriteLine("Equipment was added to pool.");
        }
    }
}
