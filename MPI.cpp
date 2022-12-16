#include <stdio.h>
#include "mpi.h"
#include<stdlib.h>
#include<math.h>
#include<bits/stdc++.h>
using namespace std;

double piAprox(double* points_array, int size) {
    int count = 0;
    for (int i = 0; i < size; i += 2) {
        if (pow(points_array[i], 2) + pow(points_array[i + 1], 2) <= 1)
            count += 1;
    }

    return (double)(count * 4) / size * 2;
}

int main(int argc, char** argv) {
    srand(time(0));
    int proccessCount;
    int N = 30000000;
    int rank;

    MPI_Init(&argc, &argv); // note that argc and argv are passed
                            // by address

    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &proccessCount);
    if (rank == 0) {
        double response[3];
        double* points_array = new double[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            points_array[i] = (double)rand() / RAND_MAX;
        }

        for (int i = 1; i < proccessCount - 1; i++) {
            int size_ = 2 * i * floor(N / (proccessCount - 1));
            double* arraytoSend = new double[size_];
            for (int j = 0; j < size_; j++) {
                arraytoSend[j] = points_array[j];
            }
            MPI_Send(&size_, 1, MPI_INT, i, 0, MPI_COMM_WORLD);

            MPI_Send(arraytoSend, size_, MPI_DOUBLE, i, 0, MPI_COMM_WORLD);

            delete[] arraytoSend;
        }
        int size_ = 2 * N;
        MPI_Send(&size_, 1, MPI_INT, proccessCount - 1, 0, MPI_COMM_WORLD);
        MPI_Send(points_array, size_, MPI_DOUBLE, proccessCount - 1, 0, MPI_COMM_WORLD);
        for (int i = 1; i < proccessCount; i++) {
            MPI_Recv(response, 3, MPI_DOUBLE, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            cout << "process " << i << "\n" << (int)response[2] / 2 << " points" << "\npi: " << response[1] << "\ntime (seconds): " << response[0] << "\n";
        }
        delete[] points_array;
    }

    else {
        int size_;
        MPI_Recv(&size_, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        double* points_array = new double[size_];
        MPI_Recv(points_array, size_, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

        clock_t time = clock();

        double pi = piAprox(points_array, size_);
        time = clock() - time;
        double response[3];
        response[0] = (double)time / CLOCKS_PER_SEC;
        response[1] = pi;
        response[2] = (double)size_;
        MPI_Send(response, 3, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD);
    }
    MPI_Finalize();
}