/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel.validator;

/**
 *
 * @author m.rogers
 */
public interface WantsValidationStatus
{

    void validateFailed();  // Called when a component has failed validation.

    void validatePassed();  // Called when a component has passed validation.
}
