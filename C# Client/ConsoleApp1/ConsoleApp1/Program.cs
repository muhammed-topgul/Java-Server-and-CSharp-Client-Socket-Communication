using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Program
    {       

        static void Main(string[] args) {

            var id = 3;

            string type = "{[get_patient_by_patient_id]-["+ id +"]}";

            Patient p = GetPatientByID(type);

            if (p != null)
            {
                Console.WriteLine("ID : " + p.Id);
                Console.WriteLine("Name : " + p.Name);
                Console.WriteLine("Email : " + p.Email);
                Console.WriteLine("Tel : " + p.Tel);
                Console.WriteLine("Age : " + p.Age);
                Console.WriteLine("Address : " + p.Address);
                Console.WriteLine("Calibre : " + p.Caliber);
                Console.WriteLine("Saç Rengi : " + p.HairColour);
                Console.WriteLine("Cinsiyet : " + p.Gender);
                Console.WriteLine("Donor : " + p.Donor);
                Console.WriteLine("Norwood Scale : " + p.Scale);
            }
           

            Console.Read();           
        }

        public static Patient GetPatientByID(string request) {
            string data = SocketConnection.SendRequest(request);
            // data =  [{2,Patient2,patient.2@gmail.com,23456,35,Berlin,kalibre2,Siyah,Kad??n,donor2,scale2}]

            Console.WriteLine("Response : " + data);

            if (!data.Equals("")) {
                data = data.Remove(data.IndexOf("["), 1);
                data = data.Remove(data.IndexOf("{"), 1);
                data = data.Remove(data.LastIndexOf("]"), 1);
                data = data.Remove(data.LastIndexOf("}"), 1);

                string[] words = data.Split(',');               

                Patient patient = new Patient(words[0], words[1], words[2], words[3], words[4], words[5], words[6], words[7], words[8], words[9], words[10]);

                return patient;
            }

            return null;
           
        }

    }
}
