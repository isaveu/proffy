/*
    Copyright (C) 2008, 2009, 2010  Paul Richards.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

#include "../Proffy/Launcher.h"

#include <windows.h>

#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <process.h>
#include <vector>

#pragma warning(disable: 4127) // conditional expression is constant

namespace {
    void SomeFunction(
        std::vector<int>* const values,
        int)
    {
        std::random_shuffle(values->begin(), values->end());
    }

    void SomeFunction(
        std::vector<int>* const values,
        float)
    {
        std::sort(values->begin(), values->end());
    }
    
    void ThreadFunction(void*)
    {
        while (true) {
            void* const pointer = calloc(20, 1000);
            free(pointer);
        }
    }
};

int main(void)
{
    std::cout << "Running dummy target.\n";

    const int count = 10000;
    std::vector<int> values;
    values.reserve(count);
    for (int i = 0; i < count; i++) {
        values.push_back(i);
    }

    {
        std::wostringstream pathToProffy;
        pathToProffy << L"../dist/bin" << (sizeof(void*)*8) << L"/Proffy" << (sizeof(void*)*8) << L".exe";
        Proffy::Launcher profiler(pathToProffy.str(), L"../dist", 1.0 / 20);
        const clock_t begin = clock();
        _beginthread(ThreadFunction, 0, NULL);
        while ((clock() - begin) / CLOCKS_PER_SEC < 3) {
            SomeFunction(&values, 3);
            SomeFunction(&values, 3.14f);
        }
    }
    std::cout << "\nDone.\n";

    return EXIT_SUCCESS;
}