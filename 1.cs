using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Serialization;

namespace GosCSharp
{
    public partial class Form1 : Form
    {
        private readonly string ComponentFileName = "itemgos.txt";
        List<ItemGos> items = new List<ItemGos>();
        public Form1()
        {
            InitializeComponent();
        }

        private void Serilaze_btn_Click(object sender, EventArgs e)
        {
            if (items.Count > 0)
            {
                saveToFile(ComponentFileName);
            }
        }

        private void Deserilaze_btn_Click(object sender, EventArgs e)
        {
            loadFromFile(ComponentFileName);
        }

        private void Add_btn_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBoxHeight.Text))
            {
                MessageBox.Show("Не заполнен вес");
                return;
            }
            if (string.IsNullOrEmpty(textBoxWeight.Text))
            {
                MessageBox.Show("Не заполнен вес");
                return;
            }
            if (string.IsNullOrEmpty(textBoxName.Text))
            {
                MessageBox.Show("Не заполнен вес");
                return;
            }
            items.Add(new ItemGos(textBoxName.Text, Convert.ToInt16(textBoxWeight.Text), Convert.ToInt16(textBoxHeight.Text)));
        }

        private void textBoxName_TextChanged(object sender, EventArgs e)
        {

        }

        private void textBoxCount_TextChanged(object sender, EventArgs e)
        {

        }

        void linqHard(int heigth)
        {
             List<ItemGos> listhard = items.Where(p=> p.height < heigth).OrderBy(p=>p.height).ToList();
        }

        void linqLite(int heigth)
        {
            List<ItemGos> listlite = (from p in items where p.height < heigth select p).ToList();
        }

        void saveToFile(string path)
        {
            XmlSerializer xmlFormat = new XmlSerializer(typeof(List<ItemGos>));
            using (Stream fStream = new FileStream(path, FileMode.Create, FileAccess.Write, FileShare.None))
            {
                xmlFormat.Serialize(fStream, items);
            }
        }


        public void loadFromFile(string path)
        {
            XmlSerializer serializer = new XmlSerializer(typeof(List<ItemGos>));
            using (Stream fStream = new FileStream(path, FileMode.Open))
            {
                using (XmlReader reader = XmlReader.Create(fStream))
                {
                    var buffItemGos = (List<ItemGos>)serializer.Deserialize(reader);
                    if (buffItemGos != null)
                    {
                        items = buffItemGos;
                        }
                    else
                    {
                        Console.WriteLine("Ошибочка");
                    }
                }
            }
        }

        private void buttonLinkLite_Click(object sender, EventArgs e)
        {
            linqLite(123);
        }

        private void link_hard_Click(object sender, EventArgs e)
        {
            linqHard(145);
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GosCSharp
{
    [Serializable]
    public class ItemGos
    {
        public string name { get; set; }
        public int weight { get; set; }
        public int height { get; set; }


        public ItemGos(string name, int weigth, int height)
        {
            this.name = name;
            this.weight = weigth;
            this.height = height;
        }

        public ItemGos() { }
    }
}
