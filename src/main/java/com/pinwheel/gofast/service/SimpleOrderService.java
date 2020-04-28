package com.pinwheel.gofast.service;

import com.google.ortools.algorithms.KnapsackSolver;
import com.pinwheel.gofast.entity.Order;
import com.pinwheel.gofast.repository.api.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SimpleOrderService implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> findByCompanyId(Long id) {
        return orderRepository.findByFrom_Company_Id(id);
    }

    /*@Override
    public List<Order> knapsack(Long id) {
        var order = orderRepository.findById(id).get();
        var pointOrders = orderRepository.findByFrom_Id(order.getFrom().getId());

        var values = profits(pointOrders);
        var weights = weights(pointOrders);

        KnapsackSolver knapsackSolver = new KnapsackSolver(KnapsackSolver.SolverType.KNAPSACK_MULTIDIMENSION_BRANCH_AND_BOUND_SOLVER, "test");
        knapsackSolver.init(
                values,
                weights,
                new long[]{order.getVehicle().getCapacity()}
        );
        final long computedValue = knapsackSolver.solve();

        ArrayList<Integer> packedItems = new ArrayList<Integer>();
        ArrayList<Long> packedWeights = new ArrayList<Long>();
        int totalWeight = 0;
        System.out.println("Total value = " + computedValue);
        for (int i = 0; i < values.length; i++) {
            if (knapsackSolver.bestSolutionContains(i)) {
                packedItems.add(i);
                packedWeights.add(weights[0][i]);
                totalWeight = (int) (totalWeight + weights[0][i]);
            }
        }

        return null;
    }*/

    @Override
    public List<Order> knapsack(Long id) {
        var order = orderRepository.findById(id).get();
        var pointOrders = orderRepository.findByFrom_Id(order.getFrom().getId());

        int capacity = order.getVehicle().getCapacity();

        int NB_ITEMS = pointOrders.size();
        // we use a matrix to store the max value at each n-th item
        int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

        // first line is initialized to 0
        for (int i = 0; i <= capacity; i++)
            matrix[0][i] = 0;

        // we iterate on items
        for (int i = 1; i <= NB_ITEMS; i++) {
            // we iterate on each capacity
            for (int j = 0; j <= capacity; j++) {
                if (pointOrders.get(i - 1).getWeight() > j)
                    matrix[i][j] = matrix[i-1][j];
                else
                    // we maximize value at this rank in the matrix
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][j -(int) pointOrders.get(i - 1).getWeight()]
                            + (int)(pointOrders.get(i - 1).getWeight() * pointOrders.get(i - 1).getCargo().getPrice().floatValue()));
            }
        }

        int res = matrix[NB_ITEMS][capacity];
        int w = capacity;
        List<Order> itemsSolution = new ArrayList<>();

        for (int i = NB_ITEMS; i > 0  &&  res > 0; i--) {
            if (res != matrix[i-1][w]) {
                itemsSolution.add(pointOrders.get(i-1));
                // we remove items value and weight
                res -= (int)(pointOrders.get(i - 1).getWeight() * pointOrders.get(i - 1).getCargo().getPrice().floatValue());
                w -= (int) pointOrders.get(i - 1).getWeight();
            }
        }

        return itemsSolution;
    }

    protected long[] profits(List<Order> orders) {
        return orders.stream()
                .map((order) -> Float.valueOf(order.getWeight() * order.getCargo().getPrice().floatValue()).longValue())
                .mapToLong(Long::valueOf)
                .toArray();
    }

    protected long[][] weights(List<Order> orders) {
        return new long[][]{
                orders.stream()
                        .map((order) -> Float.valueOf(order.getWeight()).longValue())
                        .mapToLong(Long::valueOf)
                        .toArray()
        };
    }

    private class Solution {

    }
}
