//
//  ViewController.swift
//  MJMainProject
//
//  Created by Participant on 2026-09-23.
//
// The ViewController handles the user interface. All number buttons share one numberPress action which reads the button title and builds the number on the display. All operators buttons share operatorPressed, which sends the first number to the calculator object and stores the selected operator. The equal button sends the second number to Calculator, calls calculator and display the result. The Calculator class uses and array as a stack , with addNumber pushing numbers and popNumber retrievingf them for the calcukator. 
import UIKit

class ViewController: UIViewController {
// these creates one calculator object for your buttons to use.
    @IBOutlet weak var entryLabel: UILabel!
    private let calculator = Calculator()
    //this stores the digits while the user types eg. 1,2,3 creates 123
    private var currentEntry = ""
    //this will remember whether the user selected +,-,*, or /
    private var selectedOperator = ""
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func numberPress(_ sender: Any) {
        //print ("Button was tapped")// this was entered to check if the button worked in the console
        //this will capture the number printed on whichever digit button is pressed
            guard
                let button = sender as?
                    UIButton
        else {
                return
            }
        let digit = button.configuration?.title ??
        button.currentTitle ?? ""
        // this is a debugging code that was temporarily used because the button were not accepting the func in storyboard but with this code it showed on the console.
        //print("Digit:", digit, "Before:", currentEntry)
        currentEntry += digit
        entryLabel.text = currentEntry
           
       }
    // this func is for all 4 operators. the func was created and then the empty circle was drag to each operator to assign the func
    @IBAction func operatorPressed(_ sender: Any){
        guard
            let button = sender as?
                UIButton,
            let symbol = button.configuration?.title ?? button.currentTitle,
            let number = Double(currentEntry)
        else{
            return
        }
        calculator.addNumber(number)
        selectedOperator = symbol
        currentEntry = ""
        entryLabel.text = symbol
    }
    // this func is for equal only , Double was used to be able to capture decimal
    @IBAction func equalsPressed(_ sender: Any){
        guard
            let secondNumber = Double(currentEntry),
            !selectedOperator.isEmpty
        else{
            return
        }
        calculator.addNumber(secondNumber)
        if let result = calculator.calculate(selectedOperator){
            entryLabel.text = String(result)
            currentEntry = ""
            selectedOperator = ""
            
        }
    else {
        entryLabel.text = "Error"
        currentEntry = ""
        selectedOperator = ""
        
    }
    }
}


import Foundation
class Calculator {
    private var numbers: [Double] = []
    
    public func addNumber( _ number : Double){
        numbers.append(number)
        
    }
    private func popNumber() -> Double? {
        return numbers.popLast()
    }
    public func calculate (_ operatorSymbol: String) -> Double?{
        guard
            numbers.count >= 2
        else {
            print("Please enter at least two numbers")
            return nil
        }
        guard
            let rhs = popNumber(),
            let lhs = popNumber()
        else {
            return nil
        }
        let result : Double
        
        switch
           operatorSymbol {
        case "+": result = lhs + rhs
        case "-": result = lhs - rhs
        case "*": result = lhs * rhs
        case "/":
            if rhs == 0 {
                addNumber (lhs)
                addNumber(rhs)
                return nil
            }
            result = lhs / rhs
            
        default:
            //addNumbers() calls in the error cases restore the removed numbers keeping the stack unchanged
            addNumber(lhs)
            addNumber(rhs)
            return nil
        }
        addNumber(result)
        return result
    }
    
}


