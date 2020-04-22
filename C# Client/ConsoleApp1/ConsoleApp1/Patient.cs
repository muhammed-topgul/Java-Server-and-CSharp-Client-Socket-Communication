using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    class Patient
    {
        string id;
        string name;
        string email;
        string tel;
        string age;
        string address;
        string caliber;
        string hairColour;
        string gender;
        string donor;
        string scale;

        public Patient(string id, string name, string email, string tel, string age, string address, string caliber, string hairColour, string gender, string donor, string scale)
        {
            this.Id = id;
            this.Name = name;
            this.Email = email;
            this.Tel = tel;
            this.Age = age;
            this.Address = address;
            this.Caliber = caliber;
            this.HairColour = hairColour;
            this.Gender = gender;
            this.Donor = donor;
            this.Scale = scale;
        }

        public string Id { get => id; set => id = value; }
        public string Name { get => name; set => name = value; }
        public string Email { get => email; set => email = value; }
        public string Tel { get => tel; set => tel = value; }
        public string Age { get => age; set => age = value; }
        public string Address { get => address; set => address = value; }
        public string Caliber { get => caliber; set => caliber = value; }
        public string HairColour { get => hairColour; set => hairColour = value; }
        public string Gender { get => gender; set => gender = value; }
        public string Donor { get => donor; set => donor = value; }
        public string Scale { get => scale; set => scale = value; }
    }
}
