#include <iostream>
#include <string.h>
#include <string>

struct MyObject {
	int data;
	char name;
};
struct Node {
	MyObject object;

	Node* next;
	Node* prev;

	Node() {
		next = NULL;
		prev = NULL;
	}
};


struct List {

private:
	Node* head;
	Node* tail;

public:
	List() {
		head = NULL;
		tail = NULL;
	}

	bool is_empty() {
		return head == NULL;
	}

	void addElement(MyObject object) {
		Node* current = new Node();

		current->object = object;
		current->next = NULL;

		if (head == NULL) {
			head = current;
		}
		else {
			Node* currentHead = head;
			while (currentHead->next != NULL)
			{
				currentHead = currentHead->next;
			}
			currentHead->next = current;
		}

	}

	void insert_middle(MyObject object)
	{
		Node* p = new Node;
		p->object = object;
		Node* pTemp = head;
		//Если пусто, то просто вставляем
		if (is_empty()) {
			head = p;
			tail = p;
			p->prev= NULL;
			p->next = NULL;
			return;
		}
		while (p->object.data > pTemp->object.data)
		{
			if (pTemp->next == NULL)
			{
				break;
			}
			pTemp = pTemp->next;
		}
		if (pTemp == head)
		{
			if (p->object.data > pTemp->object.data)
			{
				pTemp->next = p;
				p->prev = pTemp;
				return;
			}
			else
			{
				pTemp->prev = p;
				p->next = pTemp;
				head = p;
				return;
			}

		}
		else if (pTemp == tail)
		{
			if (p->object.data > pTemp->object.data)
			{
				pTemp->next = p;
				p->prev = pTemp;
				tail = p;
				return;
			}
			else
			{
				pTemp->prev = p;
				p->next = pTemp;
				return;
			}
		}
	}

	void printElement() {
		Node* headprint = head;
		while (headprint != NULL)
		{
			std::cout << headprint->object.data << headprint->object.name << "\n";
			headprint = headprint->next;
		}
	}

};

int main() {
	List list;
	MyObject object;
	object.data = 12;
	object.name = 'f';
	list.insert_middle(object);
	object.data = 11;
	object.name = 'a';
	list.insert_middle(object);
	object.data = 17;
	object.name = 'e';
	list.insert_middle(object);
	object.data = 13;
	object.name = 'v';
	list.insert_middle(object);
	list.printElement();
}
