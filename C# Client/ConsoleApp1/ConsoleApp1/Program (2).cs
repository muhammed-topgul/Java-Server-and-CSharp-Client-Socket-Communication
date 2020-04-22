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
        static void Main(string[] args)
        {


            string ip1 = "ip address here";           


            IPEndPoint serverAddress = new IPEndPoint(IPAddress.Parse(ip1), 5060);

            

            while (true)
            {
                Socket clientSocket = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                clientSocket.Connect(serverAddress);

                string toSend;
                Console.Write("Enter a Request: ");
                toSend = Console.ReadLine();
                // Sending 
                int toSendLen = System.Text.Encoding.ASCII.GetByteCount(toSend);
                byte[] toSendBytes = System.Text.Encoding.ASCII.GetBytes(toSend);
                byte[] toSendLenBytes = System.BitConverter.GetBytes(toSendLen);
                clientSocket.Send(toSendLenBytes);
                clientSocket.Send(toSendBytes);

                // Receiving
                byte[] rcvLenBytes = new byte[4];
                clientSocket.Receive(rcvLenBytes);
                int rcvLen = System.BitConverter.ToInt32(rcvLenBytes, 0);
                byte[] rcvBytes = new byte[rcvLen];
                clientSocket.Receive(rcvBytes);
                String rcv = System.Text.Encoding.ASCII.GetString(rcvBytes);

                Console.WriteLine("Response: " + rcv);
                //Console.Read();

                //clientSocket.Close();
            }
        }
    }
}
