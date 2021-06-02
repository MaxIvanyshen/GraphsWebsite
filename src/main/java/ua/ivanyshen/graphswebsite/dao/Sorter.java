package ua.ivanyshen.graphswebsite.dao;

public class Sorter {
    public static int[] quickSortValues(String[] params, int[] arr, int low, int high) {
        if(low>=high || arr.length==0)
            return null;
        int mid = low+(high-low)/2;
        int pivot = arr[mid];

        int i=low, j=high;
        while(i<=j) {
            while(arr[i] < pivot)
                i++;

            while(arr[j]>pivot)
                j--;

            if(i <= j) {
                int t = arr[i];
                String tStr = params[i];

                arr[i] = arr[j];
                params[i] = params[j];

                arr[j] = t;
                params[j] = tStr;
                i++;
                j--;
            }
        }
        if(low < j)
            quickSortValues(params, arr, low, j);
        if(high > i)
            quickSortValues(params, arr, i, high);

        return arr;
    }

    public static int[] quickSortValuesReverse(String[] params, int[] arr, int low, int high) {
        if(low>=high || arr.length==0)
            return null;
        int mid = low+(high-low)/2;
        int pivot = arr[mid];

        int i=low, j=high;
        while(i<=j) {
            while(arr[i] > pivot)
                i++;

            while(arr[j]<pivot)
                j--;

            if(i <= j) {
                int t = arr[i];
                String tStr = params[i];

                arr[i] = arr[j];
                params[i] = params[j];

                arr[j] = t;
                params[j] = tStr;
                i++;
                j--;
            }
        }
        if(low < j)
            quickSortValues(params, arr, low, j);
        if(high > i)
            quickSortValues(params, arr, i, high);

        return arr;
    }

    public static String[] quickSortAlphabet(String[] arr, int[] values, int low, int high) {
        if(low>=high || arr.length==0)
            return null;
        int mid = low+(high-low)/2;
        String pivot = arr[mid];

        int i=low, j=high;
        while(i<=j) {
            while(arr[i].compareTo(pivot) < 0)
                i++;

            while(arr[j].compareTo(pivot) > 0)
                j--;

            if(i <= j) {
                String t = arr[i];
                int tint = values[i];

                arr[i] = arr[j];
                values[i] = values[j];

                arr[j] = t;
                values[j] = tint;
                i++;
                j--;
            }
        }
        if(low < j)
            quickSortValues(arr, values, low, j);
        if(high > i)
            quickSortValues(arr, values, i, high);

        return arr;
    }

    public static String[] quickSortAlphabetReverse(String[] arr, int[] values, int low, int high) {
        if(low>=high || arr.length==0)
            return null;
        int mid = low+(high-low)/2;
        String pivot = arr[mid];

        int i=low, j=high;
        while(i<=j) {
            while(arr[i].compareTo(pivot) > 0)
                i++;

            while(arr[j].compareTo(pivot) < 0)
                j--;

            if(i <= j) {
                String t = arr[i];
                int tint = values[i];

                arr[i] = arr[j];
                values[i] = values[j];

                arr[j] = t;
                values[j] = tint;
                i++;
                j--;
            }
        }
        if(low < j)
            quickSortValues(arr, values, low, j);
        if(high > i)
            quickSortValues(arr, values, i, high);

        return arr;
    }
}
