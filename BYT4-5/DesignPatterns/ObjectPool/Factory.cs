using System;
using System.Collections;

namespace DesignPatterns.ObjectPool
{
    class Factory
    {
        private static int poolMaxSize = 2;
        public static readonly Queue objPool = new Queue(poolMaxSize);
        public Equipment GetEquipment()
        {
            Equipment equipment;
            
            if (objPool.Count > 0 && Equipment.equipmentMaxSize > Equipment.equipmentCounter)
                return equipment = RetrieveFromPool();
            else if (Equipment.equipmentMaxSize > Equipment.equipmentCounter)
                return equipment = new Equipment();
            else if 
                (Equipment.equipmentMaxSize == Equipment.equipmentCounter && poolMaxSize > objPool.Count) { 
                equipment = new Equipment("Equipment for pool");
                objPool.Enqueue(equipment);
                return equipment;
            }
            else
            {
                Console.WriteLine("There are maximum active equipments and pool is full.");
                return null;
            }                      
        } 
        public void DeleteEquipment(Equipment equipment)
        {
            if (Equipment.equipmentCounter == 0) {
                Console.WriteLine("There is no active equipment");
            } else if (poolMaxSize > objPool.Count)
            {
                Equipment.equipmentCounter--;
                objPool.Enqueue(equipment);
                Console.WriteLine("Equipment was deleted and added to pool.");
            } else
            {
                Equipment.equipmentCounter--;
                Console.WriteLine("Equipment was deleted.");
            }
            
        }
        protected Equipment RetrieveFromPool()
        {
            Equipment equipment;
            equipment = (Equipment)objPool.Dequeue();
                Equipment.equipmentCounter++;
            Console.WriteLine("Equipment was retrieved from pool.");
            return equipment;
        }
    }
}
