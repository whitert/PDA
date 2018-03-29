#include <thread>
#include <queue>
#include <iostream>
#include <mutex>
#include <chrono>  

using namespace std;

mutex mutx;
queue<int> q;
int produs = NULL;



void Producator(){

	while (true) {

		std::this_thread::sleep_for(std::chrono::seconds(1));

		if (produs == NULL)
			produs++;

		mutx.lock();
		if (q.size() <= 10){

			q.push(produs);
			cout << " A fost adaugat un produs " << endl;
			produs = NULL;
		}

		mutx.unlock();

	}
};


void Consumator(){

	while (true) {

		mutx.lock();
		if (q.size() > 0){

			q.pop();
			cout << "Produsul a fost scos" << endl;
		}

		mutx.unlock();

		std::this_thread::sleep_for(std::chrono::seconds(3));

	}
};


int main()
{
	while (true) {


		thread Prod(Producator);
		thread Cons(Consumator);

		Prod.join();
		Cons.join();

	}

	system("pause");

	return 0;
}

